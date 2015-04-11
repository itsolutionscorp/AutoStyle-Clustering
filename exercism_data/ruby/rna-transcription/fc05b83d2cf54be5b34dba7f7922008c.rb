class Complement
    def self.of_dna(string)
        string_new = ''
	string.each_char do |c|
	    puts c
	    if c=='G'
		string_new + 'C'
            elsif c=='C'
		string_new + 'G'
	    elsif c=='T'
		string_new + 'A'
	    else c == 'A'
		string_new + 'U'
            end
	end

    end
   

    def self.of_rna(str)
 	string_new = ''
	str.each_char do |c|
	    if c=='C'
		string_new + 'G'
            elsif c=='G'
		string_new + 'C'
	    elsif c=='A'
		string_new + 'T'
	    else c == 'U'
		string_new + 'A'
            end
	end
    string_new        
    end
end
