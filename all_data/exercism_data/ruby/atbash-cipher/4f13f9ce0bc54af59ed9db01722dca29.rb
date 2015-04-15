class Atbash
  def self.encode(s)
    s.gsub(/[^0-9a-zA-Z]/,'').downcase.chars.map do |i|
      i.match(/[0-9]/) ? i : (122 - (i.ord - 97)).chr
    end.join.gsub(/(.{5})(?!$)/, '\1 ')
  end
end
