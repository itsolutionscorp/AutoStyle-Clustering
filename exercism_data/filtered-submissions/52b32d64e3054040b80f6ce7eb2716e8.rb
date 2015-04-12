def compute(a, b)
      mutations = 0
      asplit = a.split('')
      bsplit = b.split('')
      asplit.each_with_index do |value, index|
        mutations +=1 unless asplit[index] == bsplit[index]
      end
      mutations
  end