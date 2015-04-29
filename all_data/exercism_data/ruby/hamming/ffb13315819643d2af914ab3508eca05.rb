class Array
  def splice(from, to)
    self[from, to]
  end
end

def assert_is_string(maybe_string, error_message)
  raise "Error: expected string; #{error_message}" unless maybe_string.is_a? String
end

module Hamming
  def Hamming.compute(first_string, second_string)
    assert_is_string first_string, "Hamming.compute first argument"
    assert_is_string second_string, "Hamming.compute second argument"

    shorter_length = [first_string.length, second_string.length].min

                                                # to calculate Hamming distance
    first_string.chars.zip(second_string.chars) # we'll compare chars in same position in both strings
      .splice(0, shorter_length)                # but only up to length of shorter string
      .map { |(a,b)| a == b ? 0 : 1 }           # no change needed when both elements are equal
      .inject(0) { |a,b| a+b }                  # sum all changes needed
  end
end
