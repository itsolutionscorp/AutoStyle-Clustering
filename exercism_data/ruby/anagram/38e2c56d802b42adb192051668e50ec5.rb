class Anagram
  def initialize(input)
    @input = input.strip
    @canonical_forms = {}
  end
  attr_reader :input

  def match(candidates)
    match = candidates.select { |candidate|
      canonical_form(candidate) == canonical_form(input)
    }
    match.delete_if { |candidate| candidate.downcase == input.downcase}
    match
  end

  private
  def canonical_form(input_word)
    @canonical_forms[input_word] ||= input_word.downcase.split("").sort.join
  end

end
