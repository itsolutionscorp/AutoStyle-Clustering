import string

GROUP_SIZE = 5
DICT = {}

for idx, char in enumerate(string.ascii_lowercase):
    DICT[char] = string.ascii_lowercase[-(idx + 1)]


def encode(val):
    """
    no -> ml
    O M G -> lnt
    mindblowingly -> nrmwy oldrm tob
    Testing, 1 2 3, testing. -> gvhgr mt123 gvhgr mt
    """
    val = val.lower()
    val = val.replace(" ", "")
    val = [DICT.get(c, c) for c in val if c not in string.punctuation]
    val = [val[i: i + GROUP_SIZE] for i in range(0, len(val), GROUP_SIZE)]
    val = ["".join(l) for l in val]
    return " ".join(val)


def decode(val):
    """
    vcvix rhn -> exercism
    zmlyh gzxov rhlug vmzhg vkkrm thglm v -> anobstacleisoftenasteppingstone
    """
    val = val.lower()
    val = val.replace(" ", "")
    val = [DICT.get(i, i) for i in val]
    return "".join(val)
