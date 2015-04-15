class Acronym
  def self.abbreviate(words)
    acronym = ''
    return words.split(':').first if words.include?(':')

    words.split('-').join(' ').split(' ').each do |word|
      word.chars.each_with_index do |char, i|
        if i == 0 
          acronym << char.upcase 
        else
          acronym << char if char == char.upcase && char =~ /[[:alpha:]]/
        end
      end
    end

    acronym
  end
end
