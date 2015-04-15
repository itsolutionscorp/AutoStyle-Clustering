def transform(legacy):
    new_shiny={}
    keys=legacy.keys()
    for key in keys:
        for item in legacy[key]:            
            new_shiny[item.lower()]=key
    return new_shiny
