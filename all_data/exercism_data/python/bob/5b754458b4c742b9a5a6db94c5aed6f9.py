# -*- coding: utf-8 -*-

##########
# exercism - python/bob
##########
# Model a lackadaisical teenager, using the provided testing harness to
# determine how responses should be processed.
##########

from __future__ import unicode_literals

# Define responses
_RESPONSE = {
    'meh': 'Fine. Be that way!',
    'defensive': 'Whoa, chill out!',
    'non-committal': 'Sure.',
    'dismissive': 'Whatever.'
}

# Assumed function name from test content
def hey(content):
    """Request a response from Bob, after he interprets the content."""
    pending_response = _RESPONSE['dismissive']
    if not content or not content.strip():
        # For empty content, return the expected string
        pending_response = _RESPONSE['meh']
    elif content[-1] == '?' and not _likely_yelling_in(content):
        # If you end with a ?, interpret it as a question.
        pending_response = _RESPONSE['non-committal']
    elif _likely_yelling_in(content):
        # If you provide a !, interpret it as yelling.
        pending_response = _RESPONSE['defensive']
    return pending_response

def _likely_yelling_in(content):
    """Yelling detectors live here."""
    return (
         # All upper case is yelling
         (content == content.upper())
         
         # But no letters at all means otherwise
     and (content.lower() != content.upper()) 
    )
