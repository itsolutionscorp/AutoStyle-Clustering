class Complement
   @@letters = {
	"G" => "C",
	"C" => "G",
	"T" => "A",
	"A" => "U"
   }
   def self.of_dna( dna )
   	return compute(dna,@@letters)
   end
   def self.of_rna( rna ) 
      	return compute(rna,@@letters.invert)
   end
   def self.compute(x, letters)
      	return x.split(//).map{ |x| letters[x] ? letters[x] : x}.join
   end
end
