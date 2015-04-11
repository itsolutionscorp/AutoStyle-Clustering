"""
We can see that there are 8 types of allergies and
that in this exercise they represent a binary
condition of being allergic to that thing or not.
This means that we can encode the score in a binary
representation of the decimal number and use each
binary digit to show if that allergy is present.

We need to define the class Allergies first
"""
class Allergies():
    """
    We can now use the __init__ function to take the
    score and produce an object that has the binary
    encoding.
    """
    def __init__(self, score):
        """
        Let us define the list of allergies as a list.
        This list will be ordered left to right
        """
        self.allergies_tested = ["eggs",
                                "peanuts",
                                "shellfish",
                                "strawberries",
                                "tomatoes",
                                "chocolate",
                                "pollen",
                                "cats"]
        """
        Now we can take the score as a decimal integer
        and produce the binary representation in a
        string using the format method on a string
        """
        binary_score = "{:08b}".format(score)
        """
        This represenation is MSB but our list is
        ordered LSB with eggs getting a score of 1 and
        cats getting a score of 128. This means we
        will need to reverse one of them. In this
        implementation I am reverseing the
        binary_score using a pythonic idiom of slicing
        but it is fairly arbitary decision which list
        you choose to reverse.
        """
        lsb_binary_score = binary_score[::-1]
        """
        Now we need to split the string of 0's and 1's
        that is the lsb_binary_score into a list of 
        characters we can do this using the list 
        function with the string as the input.
        """
        lsb_binary_score_list = list(lsb_binary_score)
        """
        Let us now put our two lists together to show
        which alleriges have which value 0 or 1. We
        shall us the zip function for this. The zip
        function will output a list of tuples such
        that each element of one list is paired with
        the corresponding element of the second list.
        """
        zipped_list_of_allergies = zip(
                lsb_binary_score_list,
                self.allergies_tested)
        """
        The final thing that we need to do is to set
        the list_of_allergies as a property of the
        class of Allergies and filter the list for
        alleriges that are not present. We can do the
        filtering by using the higher order function
        'filter' and passing in a predicate that only
        keeps tuples that have the first element equal
        to the character '1'.
        """
        def allergies_to_keep (allergy_tuple):
            return allergy_tuple[0] == '1'
        """
        This will return True if the score says that
        is allergy tested positive, otherwise it will
        return false.
        """
        allergies_kept_list = filter(
                allergies_to_keep,
                zipped_list_of_allergies)
        """
        Now we need to extract the list of allergies
        from the allergies_kept_list as this list has
        elements that are tuples with the second
        element being an allergy that tested positive.
        We can do this with a list comprehension as
        follows.
        """
        self.list = [allergy[1] for allergy in 
                allergies_kept_list]
        
    """
    To clear the test we need to include the method
    is_allergic_to that takes an allergy as a string
    and returns either True if the object has the
    allergy listed as testing positve or False if not.
    We can use the fact that our self.list that is
    created upon initalisation of the object has only
    the allergies that tested positive. So we can use
    the built in function 'in' to test if the allergy
    passed to the method is in the objects list of
    allergies.
    """
    def is_allergic_to(self, allergy):
        return allergy in self.list
