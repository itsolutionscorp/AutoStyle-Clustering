class Hamming
  def self.compute(strand_uno, strand_dos)
    strand_uno.each_char.each_with_index.count { |yup, abc|
      nope= strand_dos[abc] ; nope && yup != nope }
  end
end
