#ifndef _H_SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_SM
#define _H_SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : SubsystemIDAllocatorClient_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{
    // Forward declarations.
    class SubsystemIDAllocatorClient_ReceiveFSM_SM;
    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Receiving;
    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Default;
    class SubsystemIDAllocatorClient_ReceiveFSMState;
    class SubsystemIDAllocatorClient_ReceiveFSMContext;
    class SubsystemIDAllocatorClient_ReceiveFSM;

    class SubsystemIDAllocatorClient_ReceiveFSMState :
        public statemap::State
    {
    public:

        SubsystemIDAllocatorClient_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(SubsystemIDAllocatorClient_ReceiveFSMContext&) {};
        virtual void Exit(SubsystemIDAllocatorClient_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        virtual void GrantSubsystemIDTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        virtual void ReceiveTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        virtual void ReceiveTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context, GrantSubsystemID& msg, Receive::Body::ReceiveRec& transportData);
        virtual void SendTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);

    protected:

        virtual void Default(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
    };

    class SubsystemIDAllocatorClient_ReceiveFSM_SM
    {
    public:

        static SubsystemIDAllocatorClient_ReceiveFSM_SM_Receiving Receiving;
        static SubsystemIDAllocatorClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Default :
        public SubsystemIDAllocatorClient_ReceiveFSMState
    {
    public:

        SubsystemIDAllocatorClient_ReceiveFSM_SM_Default(const char *name, int stateId)
        : SubsystemIDAllocatorClient_ReceiveFSMState(name, stateId)
        {};

    };

    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Receiving :
        public SubsystemIDAllocatorClient_ReceiveFSM_SM_Default
    {
    public:
        SubsystemIDAllocatorClient_ReceiveFSM_SM_Receiving(const char *name, int stateId)
        : SubsystemIDAllocatorClient_ReceiveFSM_SM_Default(name, stateId)
        {};

        void ReceiveTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context, GrantSubsystemID& msg, Receive::Body::ReceiveRec& transportData);
    };

    class SubsystemIDAllocatorClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public SubsystemIDAllocatorClient_ReceiveFSM_SM_Default
    {
    public:
        SubsystemIDAllocatorClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : SubsystemIDAllocatorClient_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        void BroadcastLocalTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        void GrantSubsystemIDTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        void ReceiveTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
        void SendTransition(SubsystemIDAllocatorClient_ReceiveFSMContext& context);
    };

    class SubsystemIDAllocatorClient_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        SubsystemIDAllocatorClient_ReceiveFSMContext(SubsystemIDAllocatorClient_ReceiveFSM& owner)
        : FSMContext(SubsystemIDAllocatorClient_ReceiveFSM_SM::Receiving),
          _owner(owner)
        {};

        SubsystemIDAllocatorClient_ReceiveFSMContext(SubsystemIDAllocatorClient_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        SubsystemIDAllocatorClient_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        SubsystemIDAllocatorClient_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<SubsystemIDAllocatorClient_ReceiveFSMState&>(*_state));
        };

        void BroadcastGlobalTransition()
        {
            setTransition("BroadcastGlobalTransition");
            (getState()).BroadcastGlobalTransition(*this);
            setTransition(NULL);
        };

        void BroadcastLocalTransition()
        {
            setTransition("BroadcastLocalTransition");
            (getState()).BroadcastLocalTransition(*this);
            setTransition(NULL);
        };

        void GrantSubsystemIDTransition()
        {
            setTransition("GrantSubsystemIDTransition");
            (getState()).GrantSubsystemIDTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(GrantSubsystemID& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void SendTransition()
        {
            setTransition("SendTransition");
            (getState()).SendTransition(*this);
            setTransition(NULL);
        };

    private:

        SubsystemIDAllocatorClient_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_SM
