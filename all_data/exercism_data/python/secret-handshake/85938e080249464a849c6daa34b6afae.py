from collections import OrderedDict

def todays_handshake():
    """Returns a Handshake object that describes today's handshake."""
    h = Handshake()
    h.append(PhysicalMove("wink"))
    h.append(PhysicalMove("double blink"))
    h.append(PhysicalMove("close your eyes"))
    h.append(PhysicalMove("jump"))
    h.append(ReverseMove())
    return h

def handshake(code, handshake = None):
    """Encodes the provided code into a handshake sequence."""
    handshake = handshake or todays_handshake()
    return HandshakeEncoder(handshake).encode(code)

def code(sequence, handshake = None):
    """Decodes the provided handshake sequence into its code."""
    handshake = handshake or todays_handshake()
    return HandshakeDecoder(handshake).decode(sequence)


class HandshakeError(Exception): pass
class NoCodeFoundError(HandshakeError): pass
class MultipleCodesFoundError(HandshakeError): pass

class Handshake(object):
    """Registers the moves that can be used for create a handshake sequence."""

    def __init__(self):
        self.moves = OrderedDict()
        self._sequences = None

    def append(self, move):
        """Appends a move to the list of moves that are used for this Handshake.
        Each move that is added automatically gets a bit value assigned to it
        (the first move gets 1, the second 2, the third 4, etc.)
        """
        bit = 1 << len(self.moves)
        self.moves[bit] = move
        _sequences = None

    @property
    def sequences(self):
        """Returns a dictionary of all possible handshake sequences that can
        be made using the current list of moves.  The keys are integer codes,
        the values are the sequences for those codes.
        """
        if not self._sequences:
            self._sequences = dict(self._generate_sequences())
        return self._sequences

    def _generate_sequences(self):
        for code in range(0, 1 << len(self.moves)):
            yield (code, self._create_sequence_for_code(code)) 

    def _create_sequence_for_code(self, code):
        sequence = []
        for bit, move in self.moves.items():
            if code & bit:
                move.apply_to_sequence(sequence)
        return sequence 

class PhysicalMove(object):
    """Represents a physical move for the handshake ('nod', 'cry', etc.)"""

    def __init__(self, description):
        self.description = description 

    def apply_to_sequence(self, sequence):
        sequence.append(self.description) 

class ReverseMove(object):
    """Represents a move that reverses the handshake sequence so far."""

    def apply_to_sequence(self, sequence):
        sequence.reverse()

class HandshakeEncoder(object):
    """Implements encoding a code into its handshake sequence."""

    def __init__(self, handshake):
        self.handshake = handshake

    def encode(self, code):
        """Encodes the provided code into a handshake sequence.
        The code can be either a number (e.g. 13) or a string containing the
        binary representation of the code (e.g. '1101')
        When the code contains invalid data or when no sequence exists for
        the code, then an empty sequence is returned by default.
        """
        code = self._code2int(code)
        return self.handshake.sequences.get(code, [])

    def _code2int(self, code):
        try:
            if isinstance(code, basestring):
                int_value = int(code, 2)
            else:
                int_value = int(code)
        except (ValueError, TypeError): 
            return 0
        else:
            return int_value if int_value > 0 else 0

class HandshakeDecoder(object):
    """Implements decoding a handshake sequence into its code."""

    def __init__(self, handshake):
        self.handshake = handshake

    def decode(self, sequence):
        """Decodes the provided handshake sequence into the binary
        representation of its code.  When no code can be derived for the
        sequence, then '0' is returned by default.  When multiple codes
        apply, then the one with the lowest value will be returned.
        """
        matching_codes = self._get_matching_codes(sequence)
        if not matching_codes:
            return self._to_binary(0)
        return self._to_binary(min(matching_codes))

    def decode_strict(self, sequence):
        """Decodes in strict mode, which will cause an exception to be raised
        when no (NoCodeFoundError) or multple (MultipleCodesFoundError) codes
        are found that match the provided sequence.
        """
        matching_codes = self._get_matching_codes(sequence)
        if not matching_codes:
            raise NoCodeFoundError(
                "No code found for sequence %s" % sequence) 
        if len(matching_codes) > 1:
            raise MultipleCodesFoundError(
                "No unique code found for sequence %s (matching codes: %s)" \
                % (sequence, ", ".join(map(self._to_binary, matching_codes))))
        return self._to_binary(matching_codes[0])

    def _get_matching_codes(self, sequence):
        return [
            code for code in self.handshake.sequences
            if self.handshake.sequences[code] == sequence
        ]

    def _to_binary(self, code):
        return "{0:b}".format(code)
