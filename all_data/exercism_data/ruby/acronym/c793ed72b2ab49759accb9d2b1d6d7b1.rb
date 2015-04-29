class Acronym
  def self.abbreviate(long_name)
    long_name.split(' ').map { |w| word_to_abbr(w).upcase }.join
  end

  def self.word_to_abbr(word)
    num_caps = word.each_char.count { |ch| ch =~ /[A-Z]/ }
    return word[0] if num_caps == 1 or word.end_with? ':'
    return word.each_char.inject do |a, c|
      a << c if c =~ /[A-Z]/
      a
    end if num_caps > 1
    word.split('-').map { |w| w[0].upcase }.join
  end
end
