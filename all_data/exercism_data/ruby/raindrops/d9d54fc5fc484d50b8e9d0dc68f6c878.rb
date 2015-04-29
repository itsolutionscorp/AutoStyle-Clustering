class Raindrops

  def self.convert drops
    out_string = ""
    out_string += 'Pling' if drops % 3 == 0
    out_string += 'Plang' if drops % 5 == 0
    out_string += 'Plong' if drops % 7 == 0

    out_string = drops.to_s if out_string == ""

    return out_string
  end

end
