class Atbash
  @alpha = ('a'..'z').to_a
  @cipher = @alpha.reverse
  def self.encode(str)
    plain = str.gsub(/[ ,.!]/, '').downcase
    plain.each_char.with_index.inject("") { |encoded,(c,i)|
      char_lookup = @alpha.index(c)
      char_lookup.nil? ? encoded + c : encoded + @cipher[char_lookup]
    }.scan(/.{1,5}/).join(" ")
  end
end
