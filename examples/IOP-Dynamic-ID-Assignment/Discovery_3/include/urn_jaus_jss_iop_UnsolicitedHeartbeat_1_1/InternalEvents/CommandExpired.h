#ifndef URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_COMMANDEXPIRED_H
#define URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_COMMANDEXPIRED_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{

class DllExport CommandExpired: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CommandExpired();
	virtual ~CommandExpired();
};

}

#endif // URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_COMMANDEXPIRED_H
