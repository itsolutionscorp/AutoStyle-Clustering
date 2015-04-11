class Anagram
  def initialize(input)
    @input = input.strip
  end

  def match(candidates)
    candidates = reject_identical_to_input(candidates)
    find_by_normal_form(candidates,normalize(input))
  end

  private

  attr_reader :input

  def reject_identical_to_input(candidates)
    candidates.reject { |match| match.downcase == input.downcase}
  end

  def find_by_normal_form(candidates,normal_form)
    candidates.select { |candidate| normalize(candidate) == normal_form }
  end

  def normalize(input_word)
    input_word.downcase.split("").sort.join
  end

end
