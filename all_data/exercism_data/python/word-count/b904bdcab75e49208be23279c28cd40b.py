import re
import collections
import warnings


class Phrase:
    """Phrase abstraction with word counter."""
    def __init__(self, phrase, encoding=None):
        """Please specify your encoding"""
        self._io = IO_handler()
        self._phrase = self._io.hanle_in(phrase, encoding)
        self._word_count = None

    def word_count(self):
        """Count the words"""
        if self._word_count is None:
            words = re.findall("\w+", self._phrase.lower(), re.U)
            self._word_count = collections.Counter(self._io.handle_out(words))
        return self._word_count


class IO_handler:
    """Handle textual input/output.

    An utilty class aimed at handling non unicode inout/output.
    Its offer to method: hanle_in() for inputs and handle_out() for
    outputs. Both accept either string or sequences/sets  of strings.

    If all inputs are of python type 'unicode', output will be of
    the same type: if not, outputs will be of type 'str'.

    If a non unicode input is detected without encoding specified,
    a warning will be issued and utf-8 used."""
    def __init__(self):
        self._DEFAULT_ENCODING = "utf8"
        self._encoding = None
        self._to_decode = False

    def _decode_string(self, string, encoding=None):
        try:
            if type(string) is not unicode:
                self._to_decode = True
                if (encoding is None):
                    warnings.warn('Non unicode string "' + string +
                        '" was passed, but no encoding was specified, using ' +
                        self._DEFAULT_ENCODING + ' as default.',
                        UnicodeWarning)
                self._encoding = encoding or self._DEFAULT_ENCODING
                string = string.decode(self._encoding)
        except NameError:
            pass
        return string

    def hanle_in(self, data, encoding):
        try:
            data = self._decode_string(data, encoding)
        except AttributeError:
            data = [self._decode_string(string, encoding) for string in data]
        return data

    def handle_out(self, data):
        if self._to_decode:
            try:
                data = data.encode(self._encoding)
            except AttributeError:
                data = [string.encode(self._encoding) for string in data]
        return data
