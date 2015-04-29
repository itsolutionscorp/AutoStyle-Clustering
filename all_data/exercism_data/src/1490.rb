def compute(el_1, el_2)
    difference = 0
    comp_arr_1 = el_1.split("")
    comp_arr_2 = el_2.split("")
    comp_arr_1.each_with_index do |el, i|
      if el != comp_arr_2[i]
        difference += 1
      end
    end
    return difference
  end