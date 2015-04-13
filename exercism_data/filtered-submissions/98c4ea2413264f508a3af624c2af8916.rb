def compute(ancestor, genome)

    common = (ancestor.length < genome.length ? ancestor : genome).length - 1

    (0..common).inject(0) do |r, pos|
      ancestor[pos].eql?(genome[pos]) ? r : r + 1
    end
  end