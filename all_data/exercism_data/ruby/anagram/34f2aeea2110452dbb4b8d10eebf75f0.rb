class Anagram
  def initialize(anagram)
    @anagram = anagram.downcase
  end

  def match(potential_matches)
    @potential_matches = potential_matches
    return [] if invalid_arguments?
    matches = []

    @potential_matches.each do |potential_match|
      anagram_chars = @anagram.chars

      potential_match.chars.each do |potential_match_char|
        anagram_chars.slice! anagram_chars.index(potential_match_char) if anagram_chars.include? potential_match_char
      end

      matches << potential_match if anagram_chars.empty?
    end

    matches
  end

  private

  def invalid_arguments?
    return true unless @potential_matches.instance_of? Array
    clean_array!
    return true if @potential_matches.empty?
    false
  end

  def clean_array!
    @potential_matches.map! { |word| word.downcase }
    remove_improper_lengths!
    remove_exact_matches!
  end

  def remove_exact_matches!
    @potential_matches.delete @anagram
  end

  def remove_improper_lengths!
    @potential_matches.select! { |word| word.length == @anagram.length }
  end

end
