# the program is implemented in two different ways. First one based on
# iteration and secong one on recurssion. Thanks for the comments!
class Series

  attr_reader :string

  def initialize string
    @string = string.chars.map &:to_i
  end

  def slices n_consecutive_elements    
    result = Array.new
    n = string.length - n_consecutive_elements + 1
    
    raise ArgumentError if n_consecutive_elements > string.length
    n.times do 
      result << string[ 0..n_consecutive_elements - 1 ] 
      string.shift
    end
    result
  end

end

# ------------------------------------------------------------------------

class Series

  attr_reader :string, :string_length

  def initialize string
    @string = operation string
    @string_length = string.length
  end

  def slices new_string = string, result = Array.new, n_consecutive_elements
   
    raise( ArgumentError, "" ) if n_consecutive_elements > string_length 

    if new_string.size >= n_consecutive_elements
      result << new_string[ 0..n_consecutive_elements - 1 ]
      new_string.shift
      slices new_string, result, n_consecutive_elements
    else
      result
    end
  end

  def operation string
    string.chars.map &:to_i
  end

end
