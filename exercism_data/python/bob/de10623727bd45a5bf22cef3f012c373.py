#
# Skeleton file for the Python "Bob" exercise.
#
import re


def hey(what):
    """
    Simulates surly teenager.
    :param what: What you say to the simulated teenager.
    :type what: str
    :return: His response.
    :rtype: str
    """
    what = re.sub('[\s\t]*', '', what)
    if len(what) > 1:
        if what.upper() == what:
            if re.search('^[^A-Z]*$', what[:-1]):
                if what[-1] == '?':
                    return "Sure."
                return 'Whatever.'
            return "Whoa, chill out!"
        elif what[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
    return "Fine. Be that way!"
