def compute(dna1 = '', dna2 = '')
    array_dna1 = dna1.split(//)
    array_dna2 = dna2.split(//)
    distinct = 0

    if array_dna1.length == array_dna2.length
      0.upto(array_dna1.length - 1) do |i|
        array_dna1[i] != array_dna2[i] ? distinct += 1 : distinct
      end
    end
    distinct
  end