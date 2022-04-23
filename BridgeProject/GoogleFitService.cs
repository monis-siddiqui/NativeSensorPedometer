using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GoogleFitService : MonoBehaviour
{

    AndroidJavaClass unityClass;
    AndroidJavaObject unityActivity;
    AndroidJavaClass customClass;

    private void Awake()
    {
        SendActivityReference("com.kdg.toast.plugin.Bridge");
    }

    void SendActivityReference(string packageName)
    {
        unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        unityActivity = unityClass.GetStatic<AndroidJavaObject>("currentActivity");
        customClass = new AndroidJavaClass(packageName);
        customClass.CallStatic("receiveActivityInstance", unityActivity);
    }

    public int GetStepCount()
    {            //This function gives you steps recorded from and to specific date
        Debug.Log("calling functions");
        object[] args = new System.Object[2];

        args[0] = "";
        args[1] = "";
        int? stepsCount = customClass.CallStatic<int>("getStepCountData",args);
        return (int)stepsCount;
    }





    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public int getStepCountData() {
#if UNITY_EDITOR
        return 100;
#else
    return GetStepCount();
#endif
    }
}
