# Copyright (c) 2013 Tim Henigan
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

"""This module is home to the Bob class."""

class Bob:
    """Bob is a lackadaisical teenager. In conversation, his responses are
    very limited."""

    def hey(self, request):
        """Answer 'Sure.' when asked a question.
        Answer 'Woah, chill out!' if you yell (ALL CAPS).
        Answer 'Fine. Be that way!' if you address him without actually saying
        anything.
        Answer 'Whatever.' to anything else."""

        response = "Whatever."

        if len(request) == 0 or request == "":
            response = "Fine. Be that way."
        elif request.isupper():
            response = "Woah, chill out!"
        elif request.rstrip().endswith("?"):
            response = "Sure."

        return response
