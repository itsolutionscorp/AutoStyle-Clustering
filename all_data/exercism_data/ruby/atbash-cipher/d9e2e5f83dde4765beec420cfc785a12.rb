class Atbash
  def self.encode(plaintext)
    a_to_z = ("a".."z").to_a.join
    normalized_text = plaintext.downcase.gsub(/[^a-z0-9]/i,"")
    normalized_text.tr(a_to_z, a_to_z.reverse).scan(/.{,5}/).join(" ").strip
  end
end
