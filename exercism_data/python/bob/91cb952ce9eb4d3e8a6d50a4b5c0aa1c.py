#
# Skeleton file for the Python "Bob" exercise.
#
import sys, re

# Compiling these globally since they take a while per each
# These are the only good recipes I found to incorporate Unicode into the matching requirements
lowers = [u'.*[']
for i in xrange(sys.maxunicode):
    c = unichr( i )
    if c.islower():
        lowers.append(c)
lowers.append(u'].*')
lowers = "".join(lowers)
lowers_re = re.compile(lowers)

uppers = [u'.*[']
for i in xrange(sys.maxunicode):
    c = unichr( i )
    if c.isupper():
        uppers.append(c)
uppers.append(u'].*')
uppers = "".join(uppers)
uppers_re = re.compile(uppers)

def contains_lower(what):
    global lowers_re
    if lowers_re.match(what):
        return True
    return False

def contains_upper(what):
    global uppers_re
    if uppers_re.match(what):
        return True
    return False

def asking_a_question(what):
    if re.match(".*\? *$", what):
        return True
    return False

def is_shouting(what):
    # has a lowercase letter
    if contains_lower(what):
        return False
    # only whitespace
    if re.match(r'^\s+$', what):
        return False
    # contains only uppercase
    squeezed_what = re.sub(r'\W', '', what)
    if contains_upper(squeezed_what):
        return True
    return False

def not_saying_anything(what):
    if re.match(r'^\s*$', what):
        return True
    return False

def hey(what):
    if is_shouting(what):
        return 'Whoa, chill out!'
    if asking_a_question(what):
        return 'Sure.'
    if not_saying_anything(what):
        return 'Fine. Be that way!'
    return 'Whatever.'
