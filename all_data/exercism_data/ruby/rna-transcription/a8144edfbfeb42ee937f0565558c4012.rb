class Complement
  DNA = {'G' => 'C',
         'C' => 'G', 
         'T' => 'A', 
         'A' => 'U'}

  ['dna', 'rna'].each do |method|
    define_singleton_method "of_#{method}" do |seq|
      list = method == 'dna' ? DNA : DNA.invert
      seq.chars.map {|letter| list[letter]}.join('')
    end
  end 
end
