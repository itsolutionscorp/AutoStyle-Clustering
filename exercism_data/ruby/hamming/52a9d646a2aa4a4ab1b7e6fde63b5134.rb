class Hamming

  def self.compute(stranda, strandb)
    strandb = strandb.chars.to_a
    count = 0
    0.upto(strandb.length).each do |index|
      return count if self.no_strand_to_compare?(stranda[index], strandb[index])
      unless stranda[index] == strandb[index]
        count += 1
      end
    end
  end

  private
  
  def self.no_strand_to_compare?(a, b)
    a.nil? || b.nil?
  end
end
