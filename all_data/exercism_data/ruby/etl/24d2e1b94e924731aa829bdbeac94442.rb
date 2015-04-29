module ETL
  old_system = {1 => ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"],
                2 => ["D", "G"],
                3 => ["B", "C", "M", "P"],
                4 => ["F", "H", "V", "W", "Y"],
                5 => ["K"],
                8 => ["J", "X"],
                10 => ["Q", "Z"]}

  def self.transform old
    old.each_with_object({}) do |(points, letters), new|
      letters.each { |letter| new[letter.downcase] = points }
    end
  end
end
