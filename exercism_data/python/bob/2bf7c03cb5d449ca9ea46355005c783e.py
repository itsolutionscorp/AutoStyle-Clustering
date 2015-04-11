# -*- coding: utf-8 -*-
"""
    Bob
    ~~~

    This module implements the advanced
    business logic of next-gen chatbot,
    Bob.

    This is a naïve, pure-Python implementation
    with limited, single-threaded performance.
    Unsuitable for high-concurrency, high-velocity,
    low-latency applications.

"""

class Bob(object):
    """Bob the Clockwork Teenager.

    'Clockwork' means that Bob is not really
    a living human being. It is in fact just
    a cold-hearted, deterministic machine with
    no soul. This lets us avoid ethical struggles
    when employing garbage collection over
    instances of Bob.

    Instances of Bob have no internal state so
    I'm guessing they are thread-safe.

    """

    def hey(self, phrase):
        """Return answer to a given phrase.

        Bob reacts differently to exclamation, question,
        statement and empty phrase.  Due to input format,
        Bob cannot distinguish between volume,
        intonation, speed or pitch of input phrase.

        Arguments:
            phrase: a string of English text directed at Bob.

        Returns:
            A string with Bob's response.

        """

        if phrase.isupper():
            return "Woah, chill out!"
        elif phrase.endswith("?"):
            return "Sure."
        elif phrase.strip() == '':
            return "Fine. Be that way!"

        return "Whatever."
