def assert_is_string(maybe_string, error_message)
  raise "Error: expected string; #{error_message}" unless maybe_string.is_a? String
end

module Hamming
  def Hamming.compute(first_string, second_string)
    assert_is_string first_string, "Hamming.compute first argument"
    assert_is_string second_string, "Hamming.compute second argument"

                                                # to calculate Hammong distance
    first_string.chars.zip(second_string.chars) # we'll compare chars in same position in both strings
      .select { |(a,b)| !a.nil? && !b.nil? }    # nil means one string was longer, so we'll ignore them
      .map { |(a,b)| a == b ? 0 : 1 }           # no change needed when both elements are equal
      .inject(0) { |a,b| a+b }                  # sum all changes needed
  end
end
