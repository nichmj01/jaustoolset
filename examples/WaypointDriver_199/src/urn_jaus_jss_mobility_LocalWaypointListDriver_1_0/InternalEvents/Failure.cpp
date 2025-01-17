#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/InternalEvents/Failure.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Failure::getSize()
{
	unsigned int size = 0;
	
	
	return size;
}

void Failure::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

void Failure::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

Failure::Failure()
{
	m_Name = "Failure";
}

Failure::~Failure()
{
}


}
