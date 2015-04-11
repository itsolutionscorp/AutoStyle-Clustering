class Atbash

  ALPHABET = ('a'..'z').to_a

  def self.encode(subject)
    result = ''
    
    subject.gsub!(/\W+/, '')

    subject.downcase.each_char do |letter|
      if letter =~ /[a-z]/
        result << ALPHABET[(ALPHABET.index(letter)+1) * -1]
      else
        result << letter
      end
    end

    result.scan(/.{1,5}/).join(" ")
  end
end
