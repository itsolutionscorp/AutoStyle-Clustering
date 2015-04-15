class Hamming

  def Hamming.compute(strand1, strand2)
    strand1_array    = Array.new
    strand2_array    = Array.new
    strand1.each_char do |i| strand1_array.push(i) end
    strand2.each_char do |i| strand2_array.push(i) end
    aggregate_array = [strand1_array, strand2_array]
    
    # if comparing single nucleotide
    if strand1_array.count == 1 and strand2_array.count == 1 and aggregate_array[0] == aggregate_array[1] 
        return 0
    elsif strand1_array.count == 1 and strand2_array.count == 1 and aggregate_array[0] != aggregate_array[1]
        return 1 
    # else transpose array and return mutation count
    else
      mutations = Array.new
      if strand1_array.count != strand2_array.count 
        return 0
      else
      aggregate_array.transpose.each { |k, v| mutations.push(k) if k != v } 
        return mutations.count
      end
    end
  end
end
