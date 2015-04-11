module Atbash

  def self.gen_encode_table
    az = ('a'..'z').to_a 
    digits = ("0".."9").to_a
    Hash[(az + digits).zip(az.reverse + digits)]
  end

  EncodeTable = self.gen_encode_table

  def self.encode data
    data.downcase.gsub(/[^a-z0-9]/, "")
    .each_char.reduce("") { |acc, chr| acc << EncodeTable[chr] }
    .scan(/.{1,5}/).join(" ")
  end

end
