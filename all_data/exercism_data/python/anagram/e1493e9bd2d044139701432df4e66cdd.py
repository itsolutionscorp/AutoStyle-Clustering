from collections import Counter

# Check if one or more words from a list of candidates qualify as anagrams for a given word
def detect_anagrams(word,anagram_candidates):

    # Validate the inputs
    if isinstance(word, basestring) and isinstance(anagram_candidates,list):

        # List containing the matching anagrams
        result_anagrams = []

        # Set the word to lower case for doing case-insensitive comparisons
        word = word.lower()

        # Create a Counter object for keeping track of the character counts
        # and later comparing these counts against each candidate
        character_count = Counter()

        # Count the number of occurrences of a character in the word
        for character in word:
            character_count[character] += 1

        # Iterate across the list of anagram candidates
        for candidate in anagram_candidates:

            # Check if this candidate is a valid anagram for the given word
            # We send a lower case version of the candidate for case insensitive comparisons
            # We also need to send a new copy of the counter, as it will get modified during the comparison
            if is_anagram(word, candidate.lower(), character_count.copy()):
                result_anagrams.append(candidate)

        return result_anagrams

# Check if this candidate is an anagram for the word, given the count of each of its characters
def is_anagram(word,candidate,character_count):

    # If the lengths of the strings are different, don't even bother comparing them further
    if len(word) == len(candidate):

        # An equal string doesn't count as an anagram
        if word != candidate:

            # Go through the list of characters in the candidate string
            for character in candidate:

                # If the character is missing from the string altogether, or if all of its occurrences
                # in the original word have already been consumed, then we know that this is not
                # a valid anagram
                if character_count[character] == 0:
                    return False

                # Keep decreasing the count for this character
                character_count[character] -= 1

            return True
