class ETL
  def self.transform(old)
    lookup = {"a"=>1, "e"=>1, "i"=>1, "o"=>1, "u"=>1, "l"=>1, "n"=>1, "r"=>1, "s"=>1, "t"=>1, "d"=>2, "g"=>2, "b"=>3, "c"=>3, "m"=>3, "p"=>3, "f"=>4, "h"=>4, "v"=>4, "w"=>4, "y"=>4, "k"=>5, "j"=>8, "x"=>8, "q"=>10, "z"=>10}
    return_hsh = {}
    old.values.each do |value|
      value.each do |letter|
        return_hsh[letter.downcase] = lookup.values_at(letter.downcase)[0]
      end
    end
    return_hsh
  end
end
