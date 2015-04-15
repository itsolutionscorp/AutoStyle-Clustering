class Complement

  [:of_rna, :of_dna].each do |method_name|
    define_singleton_method method_name do |x|
      x.chars.map { |y| COMPLEMENTS[method_name][y.to_sym] }.reduce(:+)
    end
  end

  private

  COMPLEMENTS = {
    of_dna: {
      :G => "C",
      :C => "G",
      :T => "A",
      :A => "U"
    },
    of_rna: {
      :C => "G",
      :G => "C",
      :A => "T",
      :U => "A"
    },
  }

end
