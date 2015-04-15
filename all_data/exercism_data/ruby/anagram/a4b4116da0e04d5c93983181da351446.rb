class Anagram
  def initialize(input)
    @input = input.strip
    @normal_form = {}
  end


  def match(candidates)
    matches = candidates.select { |candidate|
      normalize(candidate) == normalize(input)
    }
    matches.delete_if { |match| match.downcase == input.downcase}
    matches
  end

  private

  attr_reader :input
  
  def normalize(input_word)
    @normal_form[input_word] ||= input_word.downcase.split("").sort.join
  end

end
