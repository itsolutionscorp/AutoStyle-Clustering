class Atbash
  @@alphabet = ('a'..'z').to_a.join

  def self.encode(string)
    result = string.downcase.gsub(/\W/,'').tr(@@alphabet,@@alphabet.reverse)
    result.chars.each_slice(5).map(&:join).join(" ")
  end

end
