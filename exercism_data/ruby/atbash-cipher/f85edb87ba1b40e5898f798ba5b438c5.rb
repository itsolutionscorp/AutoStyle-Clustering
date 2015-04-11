class Atbash
  def self.encode(plaintext)
    plaintext.downcase.scan(/\w/).map do |char|
      char =~ /[a-z]/ ? (219 - char.ord).chr : char
    end.each_slice(5).map(&:join).join(' ')
  end
end
