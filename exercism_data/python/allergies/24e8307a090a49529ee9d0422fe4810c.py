__author__ = 'cameron'


allergens = {"eggs":1, "peanuts":2,"shellfish":4, "strawberries":8,"tomatoes":16, "chocolate":32, "pollen":64,"cats":128}

so_allergens = sorted(allergens.items(), key=lambda x: x[1], reverse=True)


def Allergies(score):
    allergy_list = list()

    for sym in so_allergens:
        if score >= sym[1]:
            score -= sym[1]
            allergy_list.insert(0, sym[0])


    return allergy_list
