module Complement
  DNA_TO_RNA_COMPLEMENT = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  RNA_TO_DNA_COMPLEMENT = DNA_TO_RNA_COMPLEMENT.invert
  
  class << self
    def of_dna(dna_string)
      raise_if_string_contains_chars_other_than(dna_string, DNA_TO_RNA_COMPLEMENT.keys)
      chars_mapped_with(dna_string, DNA_TO_RNA_COMPLEMENT)
    end

    def of_rna(rna_string)
      raise_if_string_contains_chars_other_than(rna_string, RNA_TO_DNA_COMPLEMENT.keys)
      chars_mapped_with(rna_string, RNA_TO_DNA_COMPLEMENT)
    end
    
    private

    def chars_mapped_with(string, mappings)
      string.each_char.map{|char| mappings[char]}.join
    end
    
    def raise_if_string_contains_chars_other_than(string, allowed_chars)
      # I search for one disallowed character, instead of matching
      # the whole string against all allowed characters, so that
      # the regexp will exit as soon as it finds one bad char.
      disallowed_char_regex = Regexp.new("[^#{allowed_chars}]")
      raise ArgumentError if string.match(disallowed_char_regex)
    end
  end
end
