class Hamming
  def compute(mutation, normal)
    mutations = 0
    min_len = [mutation.length, normal.length].min
    (0..min_len-1).each do |num|
      mutations += 1 if mutation[num] != normal[num]
    end
    mutations
  end
end
