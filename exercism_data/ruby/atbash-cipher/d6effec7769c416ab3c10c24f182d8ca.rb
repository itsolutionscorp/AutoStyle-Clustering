module Atbash

  def self.gen_encode_table
    az = ('a'..'z').to_a 
    za = az.reverse
    digits = (0..9).map {|num| num.to_s }.to_a
    Hash[(az + digits).zip(za + digits)]
  end

  EncodeTable = self.gen_encode_table

  def self.encode data
    data = data.downcase.gsub /[^a-z0-9]/, ""
    data.each_char.with_index.map do |chr, index| 
      sep = if (index + 1) % 5 == 0 then " " else "" end
      EncodeTable[chr] + sep
    end.join.strip
  end

end
