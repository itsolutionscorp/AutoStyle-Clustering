class Atbash
  def self.encode(str)
    five_chars(str).map {|slice|
      slice.map{ |char| conversion(char) }.join
    }.join(" ")
  end

  private
    def self.five_chars(str)
      str.downcase.scan(/\w/).join.each_char.each_slice(5)
    end

    def self.conversion(char)
      char.match(/\d/) ? char : (219 - char.ord).chr
    end
end
