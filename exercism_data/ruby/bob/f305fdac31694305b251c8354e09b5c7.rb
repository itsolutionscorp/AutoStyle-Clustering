class Bob

  def initialize
  end

  def hey( *words )
    # If input is nil return response to no input
    return print response_to_saying_nothing  if words.nil? 

    # Clean up the input
    clean_words = clean_up( words.to_s )
    
    if !clean_words.say_anything?
      # If input string is empty or contains just spaces, 
      #  then @return response to saying nothing (i.e. Fine. Be that way! )
      return response_to_saying_nothing

    elsif clean_words.yelling?
      # Yelling trumps Questions--check yelling before question.
      #
      return response_to_yelling

    elsif clean_words.question?
      # If there is a question mark in string, 
      #  then @return response to a question (i.e. Sure. )
      return response_to_a_question

    else
      # Anything else, @return response to anything else (i.e. Whatever. )
      return response_to_anything_else
    end
  end

  def response_to_a_question
    # Response for a question:
    "Sure."
  end

  def response_to_yelling
    # Response to yelling: 
    "Woah, chil out!"
  end

  def response_to_saying_nothing
    # Response to nothing:
    "Fine. Be that way!"
  end

  def response_to_anything_else
    # Response to anything else:
    "Whatever."
  end


  def clean_up(words)
    # Get rid of all new line characters \n and return chars \r
    #
    # Sort through string, looking for \n & \r BUT not \\n or \\r
    # 
    # This can be accomplished using the .lines method of String class
    temp_array = words.to_s.lines
    #
    # Each element of the Array (i.e. temp_array variable) will have new line chars at end of string
    #    The strip! method is used to remove these chars & other whitespace
    temp_array.each { |x| x.strip! }
    #
    # @return the array as a single-line string
    return temp_array.join
  end

  class ::String
    # Extend the methods (below) to the String class. 
    # This allows the use of self (e.g. self.method )
    #
    def question?
      # Does +self+ contain a question mark?
      self.include?("?") ? true : false
    end

    def say_anything?
      if self.nil?
        # Check if self is valid
        return false
      elsif self.empty?
        # Check if self is just whitespace or length of 0
        return false
      else
        # Else, self must contain a non-empty input string.        
        return true
      end
    end

    def yelling?
      if self.class == Array && !self.empty?
        # Check if all alphabetical letters are capitalized 
        temp = ""
        self.each { |x| temp << x.chomp }
        temp.flatten!
        letter_count = temp.downcase.count "a-z"
        capital_count = temp.count "A-Z"
        return true   if capital_count == letter_count
      else
        letter_count = self.downcase.count "a-z"
        capital_count = self.count "A-Z"
        return true   if capital_count == letter_count
      end
    end

  end
end
