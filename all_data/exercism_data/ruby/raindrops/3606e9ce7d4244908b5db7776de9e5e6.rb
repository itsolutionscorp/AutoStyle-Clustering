class Raindrops

  def self.convert(nombre)
    phrase = ""
    codes = ["Pling", "Plang", "Plong"]
    facteurs = [3, 5, 7]
    divisibles = facteurs.map { |facteur| nombre % facteur == 0}
    if divisibles == [false, false, false]
      nombre.to_s
    else
      divisibles.each_with_index {|divisible, index| phrase << codes[index] if divisible}
      phrase
    end
  end
end
