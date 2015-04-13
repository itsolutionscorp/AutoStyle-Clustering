def compute(parent, child)
      difference = (parent - child).abs
      mutations = 0
      (parent.length - difference).times do |n|
        mutations += 1 if parent[n] != child[n]
      end
      mutations
    end