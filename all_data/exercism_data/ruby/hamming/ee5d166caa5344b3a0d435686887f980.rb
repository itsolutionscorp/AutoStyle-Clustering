################################################################################
# I'm completely new to Ruby, so please don't hold back!
#
# A few questions:
# * Is there any way to add a comment at the beginning of a line?
#   -> Would be nicer for debug only lines
# * Any better way to deal with debug in a performant way?
#   -> Of course I could add a debug method doing the same work again...
# * How would you best deal with input requirements?
#   -> is my way using "raise" at the beginning sensible?
# * How do you "RubyDoc"?
#   -> Found something similar to below, but not sure that's the way to go
################################################################################
class Hamming
  # Computes the Hamming distance between two sequences passed as String arguments
  #
  # Note: debug output can be enabled by running ruby with a argument -d
  #
  # *Parameters*
  # - +first+ -> sequence to compare to +second+
  # - +second+ -> sequence to compare to +first+
  # *Return*
  # - Hamming distance between +first+ and +second+ argument
  # *Raises*
  # - +RuntimeError+ -> if first or second are not Strings
  # - +RuntimeError+ -> if first and second are of different length
  def self.compute(first, second)
    # Requirements
    raise "Expected a String as the first parameter, but got "  << first.class.to_s  << ": " << first.to_s  unless first.is_a? String
    raise "Expected a String as the second parameter, but got " << second.class.to_s << ": " << second.to_s unless second.is_a? String
    raise "Expected two strings of equal length, but the first one was length " << first.length().to_s << ", while the second one was length " << second.length().to_s unless first.length() == second.length()
    # Business Logic
    differenceCount = 0
    differenceIndexes = "|"       #Debug
    first.split("").each_with_index do |character, index|
      if character != second[index]
        differenceCount += 1
        differenceIndexes << "x"  #Debug
      else
        differenceIndexes << " "  #Debug
      end
    end
    differenceIndexes << "|"      #Debug
    # Debug Output
    puts "|" << first << "| -> " << differenceIndexes << " <- |" << second << "|" if $DEBUG and differenceCount != 0
    # Result
    differenceCount
  end
end
