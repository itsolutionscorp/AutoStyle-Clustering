def compute(reference, mutation)
    splitted_reference = reference.split("")
    splitted_mutation = mutation.split("")
    splitted_reference
      .zip(splitted_mutation)
      .keep_if { |pair| pair[0] != pair[1] }
      .size
  end