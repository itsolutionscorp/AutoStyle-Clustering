class Hamming
  def compute(ancestor, genome)
    # get the shortest code to compare 
    common = (ancestor.length < genome.length ? ancestor : genome).length - 1
    # check the common parts of the codes looking for differences, plus 1 if it's a mutation 
    (0..common).inject(0) do |r, pos|
      ancestor[pos].eql?(genome[pos]) ? r : r + 1
    end
  end
end
