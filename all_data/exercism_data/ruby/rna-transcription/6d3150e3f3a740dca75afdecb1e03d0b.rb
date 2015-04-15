class Complement

  def self.of_dna(sequence,new_sequence="")
    sequence.chars.each_index do |t|
     if sequence[t] == 'G'
       new_sequence += 'C'
     elsif sequence[t] == 'C'
       new_sequence += 'G'
     elsif sequence[t] == 'T'
       new_sequence += 'A'
     elsif sequence[t] == 'A'
       new_sequence  += 'U'
     end
    end 
    new_sequence
  end

  def self.of_rna(sequence,new_sequence="")
    sequence.chars.each_index do |t|
     if sequence[t] == 'C'
       new_sequence += 'G'
     elsif sequence[t] == 'G'
       new_sequence += 'C'
     elsif sequence[t] == 'A'
       new_sequence += 'T'
     elsif sequence[t] == 'U'
       new_sequence  += 'A'
     end
    end 
    new_sequence
 end
  
end
