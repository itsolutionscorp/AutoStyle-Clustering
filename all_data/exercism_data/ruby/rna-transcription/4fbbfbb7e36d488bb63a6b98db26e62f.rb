class Complement

   def self.of_dna(strand)
      result = strand.split(//).map do |i|
         case i
            when 'G' then 'C'
            when 'C' then 'G'
            when 'T' then 'A'
            when 'A' then 'U'
         end
      end

      result.join
   end

   def self.of_rna(strand)
      result = strand.split(//).map! do |i|
         case i
            when 'C' then 'G'
            when 'G' then 'C'
            when 'A' then 'T'
            when 'U' then 'A'
         end
      end

      result.join
   end

end
